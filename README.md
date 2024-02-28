# Domain Modelling with Clojure

A DDD experiment.


## About

Repo for the "DDD Modeling with Clojure" workshop.

## Usage

Run the project directly, via `:main-opts` (`-m allentiak.<main-ns>`):

    $ clojure -M:run-m

Run the project's tests:

    $ clojure -T:build test

Run the project's CI pipeline and build an uberjar:

    $ clojure -T:build ci

This will produce an updated `pom.xml` file with synchronized dependencies inside the `META-INF`
directory inside `target/classes` and the uberjar in `target`. You can update the version (and SCM tag)
information in generated `pom.xml` by updating `build.clj`.

If you don't want the `pom.xml` file in your project, you can remove it. The `ci` task will
still generate a minimal `pom.xml` as part of the `uber` task, unless you remove `version`
from `build.clj**.

Run that uberjar:

    $ java -jar target/net.clojars.allentiak/<main-ns>-0.1.0-SNAPSHOT.jar


## Hacking

### Starting a REPL

Start a REPL from the terminal:

    % clojure -M:repl

### Quick Start via Github Codespaces (Web IDE)
If you have configured your Github account, you can start the project without any other setup.  It will open a web-based vscode editor backed by a Github Codespace VM. (Codespaces is Github's hosted Devcontainer solution)

[![Open in Github Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/allentiak/bob-2024--domain-modelling--clojure)

You can also clone this repo locally, and using vscode (with the devcontainer plugin), and Docker Desktop, run an isolated, fully setup version of this application locally. Open the repo in your editor and run the command `Dev Containers: Open Folder in Container...`.


## License

Copyright © 2024 Leandro Doctors

This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

You should have received a copy of the GNU Affero General Public License along with this program.
If not, see <http://www.gnu.org/licenses/>.

### Additional Permission to convey with Clojure (under GNU AGPL version 3 section 7)

If you modify this Program, or any covered work, by linking or combining it with Clojure (or a modified version of that library), containing parts covered by the terms of the Eclipse Public License (EPL), the licensors of this Program grant you additional permission to convey the resulting work.
Corresponding Source for a non-source form of such a combination shall include the source code for the parts of Clojure used as well as that of the covered work.

### Warranty Disclaimer

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU Affero General Public License for more details.
