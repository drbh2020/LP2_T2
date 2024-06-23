package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.service.EmpleadoService;
import com.example.demo.utils.Utilitarios;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	public List<EmpleadoEntity> buscarTodosEmpleados() {
		return empleadoRepository.findAll();
	}

	@Override
	public EmpleadoEntity buscarEmpleadoPorDni(String dni) {
		return empleadoRepository.findById(dni).get();
	}

	@Override
	public void crearEmpleado(EmpleadoEntity empleadoEntity, Model model) {
		
		empleadoRepository.save(empleadoEntity);
		
		model.addAttribute("registroCorrecto", "Registro Correcto");
		model.addAttribute("empleado", new EmpleadoEntity());
		
	}

	@Override
	public void actualizarEmpleado(EmpleadoEntity empleadoEntity, Model model, @PathVariable("dni") String dni) {
		
		EmpleadoEntity empleadoExistente = buscarEmpleadoPorDni(dni);
		
		if (Utilitarios.areAllFieldsNull(empleadoEntity)) {
			model.addAttribute("errorMessage", "Los campos no pueden estar vacios");
			model.addAttribute("empleado", empleadoExistente);
			model.addAttribute("dni", dni);
		}
		
		empleadoRepository.save(empleadoEntity);
		
		model.addAttribute("ActualizacionCorrecto", "Actualizacion Correcto");
	}

	@Override
	public void eliminarEmpleado(String dni) {
		empleadoRepository.deleteById(dni);
	}

}
