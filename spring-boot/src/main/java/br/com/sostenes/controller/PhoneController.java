package br.com.sostenes.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sostenes.model.Phone;
import br.com.sostenes.repository.PhoneRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PhoneController {

	@Autowired
	PhoneRepository repository;

	@RequestMapping("/phone")
	public List<Phone> getAllPhones() {
		System.out.println("Get all Phones...");

		List<Phone> phone = new ArrayList<>();
		repository.findAll().forEach(phone::add);

		return phone;
	}

	@RequestMapping("/phone/{id}")
	public Phone getPhone(@PathVariable("id") long id) {
		System.out.println("Get  Phone...");

		Phone phone = new Phone();    
		phone = repository.findById(id).get();
		
		return phone;
	}

	@PostMapping(value = "/phone/create")
	public ResponseEntity<String> postPhone(@RequestBody Phone phone) {
		String msg = null;
		HttpStatus status = null;
		try {
			verify(phone);
			msg = "Saved";
			status = HttpStatus.OK;
			repository.save(new Phone(phone.getPrice(), phone.getBrand(), phone.getPhoto(), phone.getStartDate(), phone.getEndDate(),
					phone.getCode(), phone.getColor(), phone.getModel()));
		} catch (Exception e) {
			msg = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/phone/{id}")
	public ResponseEntity<String> deletePhone(@PathVariable("id") long id) {
		System.out.println("Delete Phone with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Phone has been deleted!", HttpStatus.OK);
	}

	@PutMapping("/phone/{id}")
	public ResponseEntity<Phone> updatePhone(@PathVariable("id") long id, @RequestBody Phone phone) {
		System.out.println("Update Phone with ID = " + id + "...");

		Optional<Phone> phoneData = repository.findById(id);

		if (phoneData.isPresent()) {
			Phone _phone = phoneData.get();
			_phone.setBrand(phone.getBrand());
			_phone.setCode(phone.getCode());
			_phone.setColor(phone.getColor());
			_phone.setEndDate(phone.getEndDate());
			_phone.setStartDate(phone.getStartDate());
			_phone.setModel(phone.getModel());
			_phone.setPrice(phone.getPrice());
			_phone.setIdPhone(phone.getIdPhone());
			_phone.setPhoto(phone.getPhoto());
			return new ResponseEntity<>(repository.save(_phone), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public void verify(Phone ph) throws Exception {
		verifyCharacters("model", ph.getModel());
		verifyCharacters("brand", ph.getBrand());
		verifyCharacters("photo", ph.getPhoto());
		validateCharacters(ph.getCode());

		if (ph.getPrice().doubleValue() < 0) {
			throw new Exception("Negative Number is Invalid");
		}

	}

	public void verifyCharacters(String type, String value) throws Exception {
		if (type == "photo") {
			if (value.trim().length() > 255) {
				throw new Exception(type + " to big");
			}
		} else {
			if (value.trim().length() < 2) {
				throw new Exception(type + " to short");
			}

			if (value.trim().length() > 255) {
				throw new Exception(type + " to big");
			}
		}
	}

	public void validateCharacters(String value) throws Exception {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		if (value.length() == 8) {
			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				if (map.containsKey(c)) {
					map.put(c, map.get(c).intValue() + 1);
				} else {
					map.put(c, 1);
				}
			}

			for (Entry<Character, Integer> entry : map.entrySet()) {
				if (entry.getValue() > 1) {
					throw new Exception("Same character not allowed");
				}
			}
		} else {
			throw new Exception("Only 8 characters");
		}

	}

}
