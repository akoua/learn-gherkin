package ci.culture.learngherkin.steps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

@Slf4j
public class TestHelper {

    public static void reinitDb(JdbcTemplate jdbcTemplate, String schemaName) {
        log.debug("start reinitDb");
        cancelAllRunningQueries(jdbcTemplate);

        jdbcTemplate.queryForList(String.format("SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname='%s'",
                        schemaName), String.class)
                .stream()
                .filter(table -> !Objects.equals("flyway_schema_history", table))
                .forEach(table -> truncateTable(jdbcTemplate, table));
    }

    private static void truncateTable(JdbcTemplate jdbcTemplate, String table) {
        try {
            jdbcTemplate.update("TRUNCATE %s RESTART IDENTITY CASCADE".formatted(table));
        } catch (DataAccessException dataAccessException) {
            truncateTable(jdbcTemplate, table);
        }
    }

    private static void cancelAllRunningQueries(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("SELECT PG_CANCEL_BACKEND(pid) FROM pg_stat_activity " +
                "WHERE state = 'active' AND pid <> PG_BACKEND_PID()");
    }
}
