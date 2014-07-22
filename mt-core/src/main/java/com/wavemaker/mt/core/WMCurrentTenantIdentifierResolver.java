package com.wavemaker.mt.core;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;


/**
 * @author gauravs
 *
 */
public class WMCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private static final Logger LOG = LoggerFactory.getLogger(WMCurrentTenantIdentifierResolver.class);

    @Autowired
    private HttpServletRequest request;

    @Override
    public String resolveCurrentTenantIdentifier() {

        String tenantId = request.getHeader("WM-TenantId");

        LOG.debug(MessageFormat.format("Found TenantId=\"{0}\"", tenantId));

        return tenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
