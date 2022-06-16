package com.example.student.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.*;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static org.zalando.logbook.HeaderFilters.*;

@Configuration
@Slf4j
public class LogbookConfig {

    @Bean
    public HttpLogFormatter splunkHttpLogFormatter() {
        return new SplunkHttpLogFormatter();
    }

    @Bean
    public HttpLogWriter httpLogWriter() {
        return new InfoLevelHttpLogWriter();
    }

    static class InfoLevelHttpLogWriter implements HttpLogWriter {

        @Override
        public void write(final Precorrelation precorrelation, final String request) {
            log.info(request);
        }

        @Override
        public void write(final Correlation correlation, final String response) {
            log.info(response);
        }
    }

    @Bean
    public Logbook logbook(

            final Predicate<HttpRequest> condition,
            final CorrelationId correlationId,
            final List<HeaderFilter> headerFilters,
            final List<PathFilter> pathFilters,
            final List<QueryFilter> queryFilters,
            final List<BodyFilter> bodyFilters,
            final List<RequestFilter> requestFilters,
            final List<ResponseFilter> responseFilters,
            final Strategy strategy,
            final Sink sink) {

        return Logbook.builder()
                .condition((condition))
                .correlationId(correlationId)
                .headerFilter(removeHeaders("Transfer-Encoding"))
                .headerFilter(replaceHeaders("Content-Type", "***"))
                .queryFilters(queryFilters)
                .pathFilters(pathFilters)
                .bodyFilters(bodyFilters)
                .requestFilters(requestFilters)
                .responseFilters(responseFilters)
                .strategy(new Strategy() {
                    @Override
                    public HttpResponse process(final HttpRequest request, final HttpResponse response) {
                        return response.withoutBody();
                    }
                })
                .sink(sink)
                .build();
    }

}