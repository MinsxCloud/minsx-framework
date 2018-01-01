package com.minsx.core.system.service.api;

import org.springframework.http.ResponseEntity;

public interface SystemService {

    ResponseEntity<?> getSystemInfo();

    ResponseEntity<?> getDeveloperTeams();
}
