package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.AreaEntity;
import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.service.AreaService;
import com.example.demo.service.EmpleadoService;
import com.example.demo.utils.Utilitarios;

@Controller
public class EmpleadoController {
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private AreaService areaService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<EmpleadoEntity> listaEmpleado = empleadoService.buscarTodosEmpleados();
		model.addAttribute("listaEmpleado", listaEmpleado);
		return "listar_empleado";
	}
	
	@GetMapping("/registrar_empleado")
	public String showRegistrarEmpleado(Model model) {
		List<AreaEntity> listaArea = areaService.buscarTodosAreas();
		model.addAttribute("empleado", new EmpleadoEntity());
		model.addAttribute("listaArea", listaArea);
		return "registrar_empleado";
		
	}
	
	@PostMapping("/registrar_empleado")
	public String registrarEmpleado(@ModelAttribute EmpleadoEntity empleado, Model model ) {
		
		if (
			empleadoService.buscarEmpleadoPorDni(empleado.getDni()) != null || 
			empleadoService.buscarEmpleadoPorCorreo(empleado.getCorreo()) != null
		) {
			List<AreaEntity> listaArea = areaService.buscarTodosAreas();
			model.addAttribute("errorMessage", "El DNI o Correo del empleado ya existe");
			model.addAttribute("empleado", new EmpleadoEntity());
			model.addAttribute("listaArea", listaArea);
			return "registrar_empleado";
		}
		
		empleadoService.crearEmpleado(empleado, model);
		
		return "redirect:/";
	}
	
	@GetMapping("/detalle_empleado/{id}")
	public String verEmpleado(Model model, @PathVariable("id") String id) {
		EmpleadoEntity empleadoEncontrado = empleadoService.buscarEmpleadoPorDni(id);
		model.addAttribute("empleado", empleadoEncontrado);
		return "detalle_empleado";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminarEmpleado( @PathVariable("id") String id) {
		empleadoService.eliminarEmpleado(id);
		return "redirect:/";
	}
	
	@GetMapping("/editar_empleado/{id}")
	public String showEditarEmpleado(Model model, @PathVariable("id") String id) {
		List<AreaEntity> listaArea = areaService.buscarTodosAreas();
		model.addAttribute("empleado", empleadoService.buscarEmpleadoPorDni(id));
		model.addAttribute("listaArea", listaArea);
		return "editar_empleado";
	}
	
	@PostMapping("/editar_empleado/{id}")
	public String editarEmpleado(@ModelAttribute EmpleadoEntity empleado, Model model, @PathVariable("id") String id ) {
		
		EmpleadoEntity empleadoExistente = empleadoService.buscarEmpleadoPorDni(id);
		List<AreaEntity> listaArea = areaService.buscarTodosAreas();
		
		if (Utilitarios.areAllFieldsNull(empleado)) {
			model.addAttribute("errorMessage", "Los campos no pueden estar vacios");
			model.addAttribute("empleado", empleadoExistente);
			model.addAttribute("id", id);
			model.addAttribute("listaArea", listaArea);
			return "editar_empleado";
		}
		
		EmpleadoEntity empleadoEncontradoCorreo = empleadoService.buscarEmpleadoPorCorreo(empleado.getCorreo());
		if (
			empleadoEncontradoCorreo != null && 
			empleadoEncontradoCorreo.getCorreo() != empleadoExistente.getCorreo()  
		) {
			model.addAttribute("errorMessage", "El correo ya existe");
			model.addAttribute("empleado", empleadoExistente);
			model.addAttribute("id", id);
			model.addAttribute("listaArea", listaArea);
			return "editar_empleado";
		}
		//empleadoExistente.setDni(empleado.getDni());
		empleadoExistente.setNombre(empleado.getNombre());
		empleadoExistente.setApellido(empleado.getApellido());
		empleadoExistente.setCorreo(empleado.getCorreo());
		empleadoExistente.setDireccion(empleado.getDireccion());
		empleadoExistente.setFechaNacimiento(empleado.getFechaNacimiento());
		empleadoExistente.setAreaEntity(empleado.getAreaEntity());
		
		
		empleadoService.actualizarEmpleado(empleadoExistente, model);
		
		return "redirect:/";
	}
}
