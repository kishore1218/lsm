package cb.lms.CB_Lms.controller;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cb.lms.CB_Lms.modal.IDataEntity;
import cb.lms.CB_Lms.service.ICommonService;
import cb.lms.CB_Lms.util.ResourceEntityMapper;




/**
 * 
 * @author 1595812
 *
 */
@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class CommonEntityController {
	
	@Autowired
	ICommonService service;
	
	@GetMapping("/Ga/{action}/{id}")
	public ResponseEntity<IDataEntity> getEntity(@PathVariable("id") Integer id,@PathVariable("action")String action) {
		
		
		Class clazz=ResourceEntityMapper.getEntity(action);
		
		IDataEntity entity=service.findEntity(clazz, "id", id);	
		
		if(entity==null){
			return new ResponseEntity<IDataEntity>(HttpStatus.NOT_FOUND);
		}	
        return new ResponseEntity<IDataEntity>(entity, HttpStatus.OK);		
	}	
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/Ga/{action}")	
	public ResponseEntity<Set<IDataEntity>> getAllEntities(@PathVariable("action")String action) {
		
		Class clazz=ResourceEntityMapper.getEntity(action);
		
		Set<IDataEntity> entities=service.findAll(clazz, null, "id", true);
		
		if(entities==null){
			return new ResponseEntity<Set<IDataEntity>>(HttpStatus.NOT_FOUND);
		}	
        return new ResponseEntity<Set<IDataEntity>>(entities, HttpStatus.OK);		
	}	
	/**
	 * 
	 * @param role
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@PostMapping("/Ga/{action}")
	public ResponseEntity<Void> saveEntity(@RequestBody String json,@PathVariable("action")String action) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		
		IDataEntity entity=(IDataEntity)mapper.readValue(json, ResourceEntityMapper.getEntity(action));
		try {
			service.saveEntity(entity);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}	
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	@PutMapping("/Ga/{action}")
	public ResponseEntity<Void> updateEntity(@RequestBody String json,@PathVariable("action")String action) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		
		IDataEntity entity=(IDataEntity)mapper.readValue(json, ResourceEntityMapper.getEntity(action));
		
		try {
			service.updateEntity(entity);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	/**
	 * 
	 * @param roleId
	 * @return
	 */
	@DeleteMapping("/Ga/{action}/{id}")
	public ResponseEntity<Void> deleteEntity(@PathVariable("id") Integer id,@PathVariable("action")String action){
		
		Class clazz=ResourceEntityMapper.getEntity(action);
		
		IDataEntity entity=service.findEntity(clazz, "id", id);	
		
		try {
			service.deleteEntity(entity);
			
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);	
		}		
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);		
	}	

}
