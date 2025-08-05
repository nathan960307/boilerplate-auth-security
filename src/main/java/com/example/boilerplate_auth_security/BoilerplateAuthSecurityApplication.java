package com.example.boilerplate_auth_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoilerplateAuthSecurityApplication {

	public static void main(String[] args) {
		try {

			// .env 파일 로드
			io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure()
					.ignoreIfMissing()
					.load();

			// 환경변수 설정 (.env가 있을 경우에만)
			if (dotenv.get("JWT_SECRET") != null) {
//				System.setProperty("JDBC_DATABASE_USERNAME", dotenv.get("JDBC_DATABASE_USERNAME"));
//				System.setProperty("JDBC_DATABASE_PASSWORD", dotenv.get("JDBC_DATABASE_PASSWORD"));
//				System.setProperty("JDBC_DATABASE_URL", dotenv.get("JDBC_DATABASE_URL"));
//
//				System.setProperty("PUBLIC_SUPABASE_URL", dotenv.get("PUBLIC_SUPABASE_URL"));
//				System.setProperty("PUBLIC_SUPABASE_ANON_KEY", dotenv.get("PUBLIC_SUPABASE_ANON_KEY"));
//				System.setProperty("SUPABASE_SERVICE_ROLE", dotenv.get("SUPABASE_SERVICE_ROLE"));
//				System.setProperty("PUBLIC_STORAGE_BUCKET", dotenv.get("PUBLIC_STORAGE_BUCKET"));

				System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
				System.setProperty("JWT_ACCESS_EXPIRATION", dotenv.get("JWT_ACCESS_EXPIRATION"));
				System.setProperty("JWT_REFRESH_EXPIRATION", dotenv.get("JWT_REFRESH_EXPIRATION"));

//				System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
			}
		} catch (Exception e) {
			// GCP 환경에서는 .env가 없어도 무시하도록
			System.out.println(".env 파일이 없어도 무시하고 실행합니다.");
		}
		SpringApplication.run(BoilerplateAuthSecurityApplication.class, args);
	}

}
