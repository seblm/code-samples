import javax.inject.Inject
import observability.OpenTelemetryFilter
import play.api.http.{DefaultHttpFilters, EnabledFilters}

class Filters @Inject() (defaultFilters: EnabledFilters, openTelemetryFilter: OpenTelemetryFilter)
    extends DefaultHttpFilters(defaultFilters.filters :+ openTelemetryFilter: _*)
