package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.EmpleadoEntity;

@Service
public interface EmpleadoService {
	List<EmpleadoEntity> buscarTodosEmpleados();
	EmpleadoEntity buscarEmpleadoPorDni(String dni); // el Id
	void crearEmpleado(EmpleadoEntity usuarioEntity, Model model);
	void actualizarEmpleado(EmpleadoEntity usuarioEntity, Model model);
	void eliminarEmpleado(String dni);
}
