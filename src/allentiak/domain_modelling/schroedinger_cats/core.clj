(ns allentiak.domain-modelling.schroedinger-cats.core
  "Notes:
  - At this point, we are focusing on modeling the `entities` and `commands`.
  - At this point, there is no point in differentiating between `entities` and `aggregates`.
  - At this point, since we are focusing on only one bounded context (this is, the actual game logic), the events themselves don't need to be modeled as functions, nor they need to be passed as parameters.
  - Notwithstanding the above point, it is important to know when game `events` happen: since we are modeling everything in one file, they are being added as comments."
  (:require [clojure.spec.alpha :as s]))
