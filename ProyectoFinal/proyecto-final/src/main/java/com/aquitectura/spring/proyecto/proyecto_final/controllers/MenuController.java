package com.aquitectura.spring.proyecto.proyecto_final.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aquitectura.spring.proyecto.proyecto_final.models.Empleado;
import com.aquitectura.spring.proyecto.proyecto_final.repositories.EmpleadoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MenuController {

    private final EmpleadoRepository empleadoRepository;

    public MenuController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping({"/Menu", "/menu"})
    public String mostrarMenu(HttpSession session, Model model) {
        Object usuarioLogueado = session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/login";
        }

        List<Empleado> empleados = empleadoRepository.findAll();
        model.addAttribute("usuarioActual", usuarioLogueado.toString());
        model.addAttribute("empleados", empleados);
        return "Menu";
    }

    @PostMapping("/Menu/guardar")
    public String guardarEmpleado(
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") String telefono,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        String correoClean = correo.trim();
        if (empleadoRepository.existsByCorreo(correoClean)) {
            redirectAttributes.addFlashAttribute("error", "Ya existe un empleado con el correo '" + correoClean + "'.");
            return "redirect:/Menu";
        }

        Empleado nuevo = new Empleado(nombre.trim(), correoClean, direccion.trim(), telefono.trim());
        empleadoRepository.save(nuevo);
        redirectAttributes.addFlashAttribute("exito", "Empleado añadido correctamente.");
        return "redirect:/Menu";
    }

    @PostMapping("/Menu/actualizar")
    public String actualizarEmpleado(
            @RequestParam("id") Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("correo") String correo,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") String telefono,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        Optional<Empleado> empOpt = empleadoRepository.findById(id);
        if (empOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No se encontró el empleado a modificar.");
            return "redirect:/Menu";
        }

        String correoClean = correo.trim();
        Optional<Empleado> existenteConCorreo = empleadoRepository.findByCorreo(correoClean);
        if (existenteConCorreo.isPresent() && !existenteConCorreo.get().getId().equals(id)) {
            redirectAttributes.addFlashAttribute("error", "El correo '" + correoClean + "' ya pertenece a otro empleado.");
            return "redirect:/Menu";
        }

        Empleado empleado = empOpt.get();
        empleado.setNombre(nombre.trim());
        empleado.setCorreo(correoClean);
        empleado.setDireccion(direccion.trim());
        empleado.setTelefono(telefono.trim());
        empleadoRepository.save(empleado);

        redirectAttributes.addFlashAttribute("exito", "Empleado modificado correctamente.");
        return "redirect:/Menu";
    }

    @PostMapping("/Menu/eliminar/{id}")
    public String eliminarEmpleado(
            @PathVariable("id") Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("exito", "Empleado eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "El empleado ya no existe.");
        }
        return "redirect:/Menu";
    }

    @PostMapping("/Menu/eliminar-seleccionados")
    public String eliminarSeleccionados(
            @RequestParam(value = "ids", required = false) List<Integer> ids,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        if (ids == null || ids.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Selecciona al menos un empleado en la casilla para eliminar.");
            return "redirect:/Menu";
        }

        empleadoRepository.deleteAllById(ids);
        redirectAttributes.addFlashAttribute("exito", "Se eliminaron " + ids.size() + " registro(s) correctamente.");
        return "redirect:/Menu";
    }
}
