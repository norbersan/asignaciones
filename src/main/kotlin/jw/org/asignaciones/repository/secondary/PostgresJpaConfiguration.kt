package jw.org.asignaciones.repository.secondary

import jw.org.asignaciones.model.Appointment
import jw.org.asignaciones.model.Assignee
import jw.org.asignaciones.model.Dummy
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource
import java.util.Objects

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackageClasses = [
        Appointment::class,
        Assignee::class,
        Dummy::class
    ],
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager"
)
class PostgresJpaConfiguration {
    @Bean
    fun postgresEntityManagerFactory(
        @Qualifier("postgresDataSource") dataSource: DataSource?,
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(dataSource)
            .packages(
                Appointment::class.java,
                Assignee::class.java,
                Dummy::class.java
            )
            .build()
    }

    @Bean
    fun todosTransactionManager(
        @Qualifier("postgresEntityManagerFactory") postgresEntityManagerFactory: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager(Objects.requireNonNull(postgresEntityManagerFactory.getObject())!!)
    }
}