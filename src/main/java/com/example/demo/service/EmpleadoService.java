package com.example.demo.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.EmpleadoEntity;

public interface EmpleadoService {
	List<EmpleadoEntity> buscarTodosEmpleados();
	EmpleadoEntity buscarEmpleadoPorDni(String dni); // el Id
	EmpleadoEntity buscarEmpleadoPorCorreo(String correo); // el Id
	void crearEmpleado(EmpleadoEntity empleadoEntity, Model model);
	void actualizarEmpleado(EmpleadoEntity empleadoEntity, Model model);
	void eliminarEmpleado(String dni);
}
