package com.syne.mockusermanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.syne.mockusermanagement.rest.dto.Version;

@Service
public class VersionService {
	
	@Value( "${app.version.number}" )
	private String versionNumber;
	
	
	public Version version() {
		Version versionInfo = new Version();
		versionInfo.setVersion(versionNumber);
		
		return versionInfo;
		
	}
	
}
