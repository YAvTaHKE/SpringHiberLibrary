package ru.moerti.springprojects.config;

import jakarta.servlet.Filter;
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
        // Регистрируем фильтр для поддержки PUT/DELETE в HTML формах
        return new Filter[]{new HiddenHttpMethodFilter()};
    }
}
