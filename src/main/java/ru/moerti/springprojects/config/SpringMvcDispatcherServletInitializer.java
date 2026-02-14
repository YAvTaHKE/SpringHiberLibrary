package ru.moerti.springprojects.config;

import jakarta.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import jakarta.servlet.ServletContext; //Для новых версий Spring
import jakarta.servlet.ServletException; //Для новых версий Spring

public class SpringMvcDispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?> [] getRootConfigClasses() {
        return null; //Если нет корневой конфигурации
    }

    @Override
    protected Class<?> [] getServletConfigClasses() {
        return new Class[]{SpringConfig.class}; //мой конфигурационный файл
    }
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // Все запросы идут через DispatcherServlet
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext); // Обязательно вызвать!
        // Дополнительная инициализация, если нужна
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8"); // Используй UTF-8 для всех запросов и ответов
        encodingFilter.setForceEncoding(true); // заставляет применить UTF-8, даже если в запросе указана другая кодировка

        return new Filter[] {
                encodingFilter,
                new HiddenHttpMethodFilter()
        };
    }
}
