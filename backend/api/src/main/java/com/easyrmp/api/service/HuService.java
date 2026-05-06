package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Hu;
import com.easyrmp.api.repository.HuRepository;

//Clase service donde se gestiona la logica de negocio
@Service
public class HuService {

    @Autowired
    HuRepository huRepository;

    //Obtiene una lista con las HUs existentes
    public ResponseEntity<?> obtenerHus() {
        return ResponseEntity.ok(huRepository.findAll());
    }

    //Busca una HU concreta segun su id (codigo SSCC) y si la encuentra devuelve el objeto entero con sus datos.
    public ResponseEntity<?> huPorSSCC(Long sscc) {

        Hu hu = huRepository.findById(sscc).orElse(null);
        if (hu != null){
            return new ResponseEntity<>("HU encontrada: " + hu, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("HU no encontrada con SSCC" + sscc, HttpStatus.NOT_FOUND);
        }
    }

    //Añade una nueva HU que recibe como parametro, antes de eso comprueba si no existe ya en la base de datos
    public ResponseEntity<?> addHu(Hu nuevaHu) {

        if (huRepository.existsById(nuevaHu.getSscc())){
            return new ResponseEntity<>("Error: HU ya registrada con SSCC " + nuevaHu.getSscc(),HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            huRepository.save(nuevaHu);
            return new ResponseEntity<>("HU registrada con exito: " + nuevaHu, HttpStatus.OK);
        }
    }

    //Elimina una HU, antes, comprueba que exista en la base de datos
    public ResponseEntity<?> eliminarHu(Long sscc) {

        Hu hu = huRepository.findById(sscc).orElse(null);

        if (hu != null){
            huRepository.delete(hu);
            return new ResponseEntity<>("HU eliminada con exito: " + hu, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se ha encontrado ninguna HU con el SSCC " + sscc,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //Edita una HU existente, mediante su sscc, busca si existe y si existe la modifica.
    public ResponseEntity<?> editarHu(Long sscc, Hu huEditada) {

        if (huRepository.existsById(sscc)){
            huRepository.save(huEditada);
            return new ResponseEntity<>("HU modificada con exito: " + huEditada, HttpStatus.OK);
        }

        return new ResponseEntity<>("No existe ninguna HU con SSCC " + sscc,HttpStatus.NOT_MODIFIED);
    }

}
