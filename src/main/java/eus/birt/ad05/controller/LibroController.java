package eus.birt.ad05.controller;
import eus.birt.ad05.model.Libro; import eus.birt.ad05.service.LibroService; import jakarta.validation.Valid; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*; import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/libros") public class LibroController {
 private final LibroService service; public LibroController(LibroService s){service=s;}
 @GetMapping public String listar(Model m){m.addAttribute("libros",service.listar());return "libros/lista";}
 @GetMapping("/nuevo") public String nuevo(Model m){m.addAttribute("libro",new Libro());return "libros/formulario";}
 @GetMapping("/{id}/editar") public String editar(@PathVariable Long id,Model m){m.addAttribute("libro",service.buscar(id));return "libros/formulario";}
 @PostMapping("/guardar") public String guardar(@Valid @ModelAttribute Libro libro,BindingResult br,RedirectAttributes ra){if(br.hasErrors())return "libros/formulario";if(libro.getId()==null)service.guardar(libro);else service.actualizar(libro.getId(),libro);ra.addFlashAttribute("mensaje","Libro guardado correctamente");return "redirect:/libros";}
 @GetMapping("/{id}/eliminar") public String confirmar(@PathVariable Long id,Model m){m.addAttribute("libro",service.buscar(id));return "libros/confirmar";}
 @PostMapping("/{id}/eliminar") public String eliminar(@PathVariable Long id,RedirectAttributes ra){service.eliminar(id);ra.addFlashAttribute("mensaje","Libro y sus capitulos eliminados");return "redirect:/libros";}
}
