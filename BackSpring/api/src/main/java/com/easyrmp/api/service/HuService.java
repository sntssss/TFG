package com.easyrmp.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.easyrmp.api.model.Hu;
import com.easyrmp.api.repository.HuRepository;

@Service
public class HuService {

    @Autowired
    HuRepository huRepository;

    public ResponseEntity<?> obtenerHus() {
        return ResponseEntity.ok(huRepository.findAll());
    }

    public ResponseEntity<?> usuarioPorSSCC(Long sscc) {

        Hu hu = huRepository.findById(sscc).orElse(null);
        if (hu != null){
            return new ResponseEntity<>(hu, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(hu, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addHu(Hu nuevaHu) {

        if (huRepository.existsById(nuevaHu.getSscc())){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            huRepository.save(nuevaHu);
            return new ResponseEntity<>(nuevaHu, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> eliminarHu(Hu hu) {

        if (huRepository.existsById(hu.getSscc())){
            huRepository.delete(hu);
            return new ResponseEntity<>(hu, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<?> editarHu(Long sscc, Hu hu) {

        if (huRepository.existsById(sscc)){
            huRepository.save(hu);
            return new ResponseEntity<>(hu, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
