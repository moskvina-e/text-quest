package com.javarush.quest.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EncodingFilterTest {

    @Mock
    ServletRequest servletRequest;
    @Mock
    ServletResponse servletResponse;
    @Mock
    FilterChain filterChain;

    private EncodingFilter filter;
    private static final String encoding = "UTF-8";
    private static final String contentType = "text/html; charset=UTF-8";

    @BeforeEach
    void setUp() {
        filter = new EncodingFilter();
    }

    @Test
    @DisplayName("Test call setCharacterEncoding for servletRequest")
    void testCallSetCharacterEncodingForServletRequest() throws ServletException, IOException {
        filter.doFilter(servletRequest, servletResponse, filterChain);
        verify(servletRequest).setCharacterEncoding(encoding);
    }

    @Test
    @DisplayName("Test call setCharacterEncoding for servletResponse")
    void testCallSetCharacterEncodingForServletResponse() throws ServletException, IOException {
        filter.doFilter(servletRequest, servletResponse, filterChain);
        verify(servletResponse).setCharacterEncoding(encoding);
    }

    @Test
    @DisplayName("Test call setContentType for servletResponse")
    void testCallSetContentTypeForServletResponse() throws ServletException, IOException {
        filter.doFilter(servletRequest, servletResponse, filterChain);
        verify(servletResponse).setContentType(contentType);
    }

    @Test

    void testFilterChainCallDoFilter() throws ServletException, IOException {
        filter.doFilter(servletRequest, servletResponse, filterChain);
        verify(filterChain, times(1)).doFilter(servletRequest,servletResponse);
    }
}