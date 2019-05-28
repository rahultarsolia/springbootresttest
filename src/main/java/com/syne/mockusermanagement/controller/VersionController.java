package com.syne.mockusermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syne.mockusermanagement.rest.dto.Version;
import com.syne.mockusermanagement.service.VersionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/version")
public class VersionController {
	private final VersionService versionService;
	
	@GetMapping("")
	public Version getVersion() {
		return versionService.version();
	}
	

}
