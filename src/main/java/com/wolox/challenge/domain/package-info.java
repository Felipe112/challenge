/**
 * @author Andres F. Ceballos
 * 
 *         Paquete creado para almacenar los Dominios, modulos o funcionalidades
 *         las cuales poseen una logica de negocio particular. Esta segregación
 *         se emplea buscando poder tener una alta cohexión y un bajo
 *         acoplamiento en las funcionalidades o modulos que pueda tener la
 *         aplicación.
 * 
 *         Nota: Esta organización de paquetes lo que busca principalmente es
 *         poder a futuro brindar un orden y claridad para agilizar los temas de
 *         mejoras o bug, partiendo de que las funcionalidades o metodos
 *         relacionados con un módulo (dominio, funcionalidad) se encuentran en
 *         la misma parte.
 * 
 *         Dentro de cada modulo se pueden llegar a tener diversas
 *         caracteristicas.
 * 
 *         * Business (Empleado para realizar la lógica del negocio)
 * 
 *         * Repository (Comunicación con servicios o componentes externos)
 * 
 *         * DTO (Estructuras de objetos empleados dentro del módulo.)
 * 
 */
package com.wolox.challenge.domain;