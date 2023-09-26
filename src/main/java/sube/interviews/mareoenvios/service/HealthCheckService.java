package sube.interviews.mareoenvios.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheckService {
    private static final Logger LOGGER = LogManager.getLogger(HealthCheckService.class);

    @GetMapping()
    public String check() {
        LOGGER.info("Health Check Ok");
        return "Health Check Ok";
    }
}