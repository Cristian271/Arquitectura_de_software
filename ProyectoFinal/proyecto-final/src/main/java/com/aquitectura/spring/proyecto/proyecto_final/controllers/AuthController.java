package com.aquitectura.spring.proyecto.proyecto_final.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aquitectura.spring.proyecto.proyecto_final.models.Credencial;
import com.aquitectura.spring.proyecto.proyecto_final.repositories.CredencialRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final CredencialRepository credencialRepository;

    public AuthController(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    @GetMapping({"/", "/login"})
    public String mostrarLogin(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/Menu";
        }
        if (!model.containsAttribute("modoRegistro")) {
            model.addAttribute("modoRegistro", false);
        }
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/Menu";
        }
        model.addAttribute("modoRegistro", true);
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam("usuario") String usuario,
            @RequestParam("contrasena") String contrasena,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        String userClean = usuario != null ? usuario.trim() : "";
        Optional<Credencial> credencialOpt = credencialRepository.findByUsuarioAndContrasena(userClean, contrasena);

        if (credencialOpt.isPresent()) {
            session.setAttribute("usuarioLogueado", credencialOpt.get().getUsuario());
            return "redirect:/Menu";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos.");
            return "redirect:/login";
        }
    }

    @PostMapping("/registro")
    public String registrarCuenta(
            @RequestParam("usuario") String usuario,
            @RequestParam("contrasena") String contrasena,
            RedirectAttributes redirectAttributes) {

        String userClean = usuario != null ? usuario.trim() : "";

        if (userClean.isEmpty() || contrasena == null || contrasena.isBlank()) {
            redirectAttributes.addFlashAttribute("errorRegistro", "Debes completar el usuario y la contraseña.");
            redirectAttributes.addFlashAttribute("modoRegistro", true);
            return "redirect:/login";
        }

        if (credencialRepository.existsByUsuario(userClean)) {
            redirectAttributes.addFlashAttribute("errorRegistro", "El nombre de usuario '" + userClean + "' ya está registrado.");
            redirectAttributes.addFlashAttribute("modoRegistro", true);
            return "redirect:/login";
        }

        Credencial nuevaCredencial = new Credencial(userClean, contrasena);
        credencialRepository.save(nuevaCredencial);

        redirectAttributes.addFlashAttribute("exito", "Cuenta creada exitosamente. Ya puedes iniciar sesión con tu usuario.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
