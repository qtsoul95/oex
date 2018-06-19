package com.example.oex.web.config

import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*
import javax.sql.DataSource

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@ComponentScan("com.example.oex.common")
@Configuration
class AppConfig(
    private val env: Environment
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"))
        dataSource.url = env.getProperty("spring.datasource.url")
        dataSource.username = env.getProperty("spring.datasource.username")
        dataSource.password = env.getProperty("spring.datasource.password")

        return dataSource
    }

    @Bean
    fun sessionFactory(dataSource: DataSource): SessionFactory {
        val properties = Properties()
        properties["hibernate.dialect"] = env.getProperty("spring.jpa.properties.hibernate.dialect")
        properties["hibernate.show_sql"] = env.getProperty("spring.jpa.show-sql")
        properties["current_session_context_class"] = env.getProperty("spring.jpa.properties.hibernate.current_session_context_class")

        val localSessionFactoryBean = LocalSessionFactoryBean()
        localSessionFactoryBean.setPackagesToScan("com.example.oex.common")
        localSessionFactoryBean.setDataSource(dataSource)
        localSessionFactoryBean.hibernateProperties = properties
        localSessionFactoryBean.afterPropertiesSet()

        return localSessionFactoryBean.`object` as SessionFactory
    }

    @Bean
    fun transactionManager(sessionFactory: SessionFactory): HibernateTransactionManager =
        HibernateTransactionManager(sessionFactory)
}
