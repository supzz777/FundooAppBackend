package com.bridgelabz.spring.fundoo.user.utility;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.bridgelabz.spring.fundoo.user.exception.custom.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtility {

	// private static final String SECRET_KEY="secret";
	// ( itz not a good pratice to hardcode in the program.nReal scenario write this
	// in application.properties file.)

	// ---------------------------------------------------------------------------//

	// creates the token with the passed string , here it is email.
	public String createToken(String email) {

		// creating the token .
		return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS256, UserUtility.SECRET_KEY).compact();
	}

	// --------------------------------------------------------------------------------//

	// takes the token and returns the inner content we have passed to it , here it
	// is email id of the user.
	public String decodeToken(String token) {
		/*
		 * Claims-based authentication is a mechanism which defines how applications
		 * acquire identity information about users... The claims in a JWT are encoded
		 * as a JSON object that is digitally signed using JSON Web Signature (JWS)
		 * and/or encrypted using JSON Web Encryption (JWE).
		 */
		Claims claimz = null;
		try {

			System.out.println("token :-" + token);

			// to decode and verify the code.
			claimz = Jwts.parser() /*
									 * Parsing ---> in Java methods means that the method is taking input from a
									 * string and returning some other data type. Definition of parse. The actual
									 * definition of "parse" in Wiktionary is
									 * "To split a file or other input into pieces of data that can be easily stored or manipulated."
									 */
					.setSigningKey(UserUtility.SECRET_KEY).parseClaimsJws(token).getBody();

			System.out.println(claimz.getSubject()); // returning the subject i.e our email id.

		} catch (Exception e) {
			throw new TokenException(UserUtility.INVALID_TOKEN);
		}

		// returning the passed information i.e email id in this case, while creating
		// the token.
		return claimz.getSubject();

	}

	// ------------------------------------------------------------------------------------------//

}
