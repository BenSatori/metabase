(ns metabase.sync.schedules-test
  (:require [clojure.test :refer :all]
            [metabase.sync.schedules :as sync.schedules]))

(deftest schedule-map->cron-strings-test
  (is (= {} (sync.schedules/schedule-map->cron-strings {})))
  (is (= {:cache_field_values_schedule "0 0 4 * * ? *"}
         (sync.schedules/schedule-map->cron-strings {:cache_field_values {:schedule_type "daily"
                                                                          :schedule_hour 4}})))
  (is (= {:metadata_sync_schedule      "0 0 * * * ? *",
          :cache_field_values_schedule "0 0 4 * * ? *"}
         (sync.schedules/schedule-map->cron-strings {:cache_field_values {:schedule_type "daily"
                                                                          :schedule_hour 4}
                                                     :metadata_sync      {:schedule_type "hourly"}}))))
