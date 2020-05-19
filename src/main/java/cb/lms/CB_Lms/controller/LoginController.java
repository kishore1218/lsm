package cb.lms.CB_Lms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cb.lms.CB_Lms.modal.Faculty;
import cb.lms.CB_Lms.modal.LoginAccount;
import cb.lms.CB_Lms.service.CommonService;
import cb.lms.CB_Lms.service.IUserService;
import cb.lms.CB_Lms.service.JWTService;
import cb.lms.CB_Lms.to.JWTResponse;
import cb.lms.CB_Lms.util.BusinessConstants;

/**
 * 
 * @author 1595812
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	@Autowired
	IUserService userService;

	@Autowired
	CommonService entityService;

	@Autowired
	JWTService jwtservice;

	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<JWTResponse> authenticate(@RequestBody LoginAccount loginAct) {

		JWTResponse jwt = null;

		try {
			Faculty dbUser = userService.retrieveUserByCredentials(loginAct.getUserName(), loginAct.getPassword());

			if (dbUser != null) {
				jwt = generateAuthToken(dbUser);

			} else {
				return new ResponseEntity<JWTResponse>(HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<JWTResponse>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<JWTResponse>(jwt, HttpStatus.OK);
	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<JWTResponse> refreshToken(HttpServletRequest request) {

		String token = request.getHeader("refresh-token");

		try {
			if (token != null) {
				String jwtRefreshToken = token.substring(7);
				if (jwtservice.validateToken(jwtRefreshToken)) {

					Faculty dbUser = (Faculty) entityService.findEntity(Faculty.class, "id",
							jwtservice.getUserIdFromClaim(jwtRefreshToken));

					JWTResponse jwt = generateAuthToken(dbUser);

					return new ResponseEntity<JWTResponse>(jwt, HttpStatus.OK);

				}
			} else {
				return new ResponseEntity<JWTResponse>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JWTResponse>(HttpStatus.NOT_FOUND);
	}

	private JWTResponse generateAuthToken(Faculty user) {

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("token-type", "auth-token");
		claims.put("role", user.getRole().getId());
		claims.put("userId", user.getId());

		String authtoken = jwtservice.generateToken(user.getLastName() + user.getFirstName(), claims);

		Map<String, Object> refreshClaims = new HashMap<String, Object>();
		refreshClaims.put("userId", user.getId());
		claims.put("token-type", "refreshtoken");

		String refreshtoken = jwtservice.refreshToken(user.getLastName() + user.getFirstName(), refreshClaims);

		return new JWTResponse(authtoken, refreshtoken);

	}

}
