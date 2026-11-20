package eus.birt.ad05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eus.birt.ad05.model.Capitulo;
import eus.birt.ad05.service.CapituloService;
import eus.birt.ad05.service.LibroService;
import eus.birt.ad05.service.RecursoNoEncontradoException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/capitulos")
public class CapituloController {

    private final CapituloService service;
    private final LibroService libros;

    public CapituloController(CapituloService service, LibroService libros) {
        this.service = service;
        this.libros = libros;
    }

    @ModelAttribute
    public void opciones(Model model) {
        model.addAttribute("libros", libros.listar());
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("capitulos", service.listar());
        return "capitulos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model, RedirectAttributes redirectAttributes) {
        if (!libros.hayLibros()) {
            redirectAttributes.addFlashAttribute(
                    "error", "Primero debes crear un libro");
            return "redirect:/libros";
        }

        model.addAttribute("capitulo", new Capitulo());
        return "capitulos/formulario";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("capitulo", service.buscar(id));
        return "capitulos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(
            @Valid @ModelAttribute Capitulo capitulo,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "capitulos/formulario";
        }

        try {
            if (capitulo.getId() == null) {
                service.guardar(capitulo);
            } else {
                service.actualizar(capitulo.getId(), capitulo);
            }
        } catch (IllegalArgumentException | RecursoNoEncontradoException e) {
            model.addAttribute("error", e.getMessage());
            return "capitulos/formulario";
        }

        redirectAttributes.addFlashAttribute(
                "mensaje", "Capitulo guardado correctamente");
        return "redirect:/capitulos";
    }

    @GetMapping("/{id}/eliminar")
    public String confirmar(@PathVariable Long id, Model model) {
        model.addAttribute("capitulo", service.buscar(id));
        return "capitulos/confirmar";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        service.eliminar(id);
        redirectAttributes.addFlashAttribute(
                "mensaje", "Capitulo eliminado; el libro se conserva");
        return "redirect:/capitulos";
    }
}
