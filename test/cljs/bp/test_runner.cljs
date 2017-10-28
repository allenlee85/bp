(ns bp.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [bp.core-test]
   [bp.common-test]))

(enable-console-print!)

(doo-tests 'bp.core-test
           'bp.common-test)
