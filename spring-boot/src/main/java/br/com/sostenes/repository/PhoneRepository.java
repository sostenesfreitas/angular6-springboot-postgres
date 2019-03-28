package br.com.sostenes.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.sostenes.model.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Long> {

}
