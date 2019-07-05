Graphiti Changelog
==================

## Release version X.Y.Z
*XXXX.XX.XX*

### New Feature

### Changes

### Bug fix


## Release version 1.13.6
*2019.07.05*

### New Feature

### Changes
* Releng:
  * Update jacoco/sonar config;

### Bug fix


## Release version 1.13.5
*2019.07.05*

### New Feature

### Changes
* Releng:
  * Update jacoco/sonar config;

### Bug fix


## Release version 1.13.4
*2019.07.05*

### New Feature

### Changes
* Releng update:
  * Change mirror (ibcp down);
  * Fix travis config;

### Bug fix


## Release version 1.13.3
*2019.06.25*

### New Feature

### Changes
* Releng update
  * Jacoco 0.8.3 -> 0.8.4
  * Sonar 3.4.1.1168 -> 3.6.0.1398
  * Tycho 1.4.0 -> 1.3.0
  * Checkstyle 8.18 -> 8.22
  * RCPTT 2.3.0 -> 2.4.2
  * Drop support for 32 bits systems
  * Remove Eclipse config for Xtend projects (as graphiti does not have xtend files);

### Bug fix


## Release version 1.13.2
*2019.05.24*

### New Feature

### Changes
* Move xerces to 2.12.0+;
* Update tycho to 1.3.0;
* Upate Jacoco to 0.8.3;
* Update checkstyle to 8.18;
* Fix PDE warnings;

### Bug fix


## Release version 1.13.1
*2018.12.19*

### New Feature

### Changes
* Force writing of XML namespaces;

### Bug fix


## Release version 1.13.0
*2018.12.18*

### New Feature

### Changes
* Use standard format when writing XML files;

### Bug fix


## Release version 1.12.0
*2018.11.23*

### New Feature

### Changes
* Releng updates;

### Bug fix


## Release version 1.11.1
*2018.11.21*

### New Feature

### Changes
* Releng updates;

### Bug fix


## Release version 1.11.0
*2018.11.08*

### New Feature

### Changes
* Use new M2E Code Quality P2 repo;
* Refactoring;
* Add java 10+ compatibility (missing javax.annotations);
* Fix travis file;

### Bug fix


## Release version 1.10.0
*2018.08.31*

### New Feature

### Changes
* Update coding policies to 1.3.0;
* Update external dependencies to 3.3.0;

### Bug fix


## Release version 1.9.1
*2018.07.05*

### New Feature

### Changes
* Update tycho to 1.2.0;
* Update to Java10 compatibility;
* Update to JUnit 5

### Bug fix


## Release version 1.9.0
*2018.06.29*

### New Feature

### Changes
* Fix checkstyle configuration;
* Update to Eclipse Photon;
* Update Xtend/Xtext to 2.14.0+;

### Bug fix


## Release version 1.8.1
*2018.06.13*

### New Feature

### Changes
* Enable travis_retry when fetching dependencies;
* Update Checkstyle and change update site URL to bintray;
* Force externaldeps to [3.2.0,4.0.0);

### Bug fix


## Release version 1.8.0
*2018.06.01*

### New Feature

### Changes
* Update manifest files;
* Replace use of deprecated API;
* Force externaldeps to 3.1.0+;

### Bug fix


## Release version 1.7.0
*2018.05.25*

### New Feature

### Changes
* Change implementation PseudoGraph instead of MultiGraph;

### Bug fix


## Release version 1.6.0
*2018.03.29*

### New Feature

### Changes
* Update mailmap file;
* Force externaldeps to 3.0.0+;
* Fix release script;
* Refactor code (remove sonar code smells);
* Throw exceptions instead of printing them in stderr;
* Move deploy server to preesm.insa-rennes.fr;
* Update project info (URL);

### Bug fix


## Release version 1.5.0
*2018.02.05*

### New Feature

### Changes
* Update JGrapht to 1.1.0
* Force externaldeps to 2.0.0+

### Bug fix


## Release version 1.4.7
*2018.01.11*

### New Feature

### Changes
* update release scripts (including travis and jenkins files)
* update checkstyle to 8.5
* use Maven plugins and coding policies from Maven Central instead of Preesm own maven repo

### Bug fix


## Release version 1.4.6
*2017.12.19*

### New Feature

### Changes
* Update releng scripts;
* Add dependency to RCPTT in the dev feature;
* disable javadoc generation (source is available via the Dev Features and source plugins);

### Bug fix

## Release version 1.4.5
*2017.11.14*

### New Feature

### Changes
* Add dependencies to Ecore Diagram Tools, Xcore and Sonarlint (dev feature), and CDT (dev + normal feature);
* Force externaldeps to 1.3.0+

### Bug fix

## Release version 1.4.4
*2017.10.31*

### New Feature

### Changes
* Add travis file;
* Update project info in POM file;

### Bug fix

## Release version 1.4.3
*2017.10.17*

### New Feature
* Add feature dependency to TM Terminal to have easy terminal access;
* Add feature dependency to Graphiti SDK Plus source;

### Bug fix
* Fix Palette rendering: use deprecated method for compatibility with pre-Oxygen based distros;

## Release version 1.4.2
*2017.08.17*

### New Feature

### Changes
* Upgrade to Eclipse Oxygen;
* Rename extension point;

### Bug fix

## Release version 1.4.1
*2017.07.18*

### Changes
* Cleanup releng files
* Normalize feature licenses

## Release version 1.4.0
*2017.06.26*

### New Feature
* Add test plug-in fragments for future test campaigns
* Build process now produces
  * a source feature (include source code)
  * a 'meta' feature including all development requirements for Graphiti
  * The aggregated Javadoc
* Maven build process allows to automatically deploy on SourceForge server

### Changes
* The build process does not require DFTools and Preesm source code anymore
  * It uses the Preesm complete repo to lookup missing OSGi dependencies (see URL in pom.xml)
* Third party dependencies are moved to external OSGi dependencies instead of jar archives within projects. See https://github.com/preesm/externaldeps
* The jgrapht 0.8.2 project has been removed, and becomes an OSGi dependency (see above line)
* The bundle and project names does not show any relation with DFTools
* Add checkstyle hook on Maven build to enforce code style
  * Config file is ./releng/VAADER_checkstyle.xml
  * Installable pre-commit hook in ./releng/hooks/
* Cleanup and Format code using Eclipse template that respects checkstyle config file
  * Eclipse preferences under ./releng/VAADER_eclipse_preferences.epf
* Update charset and line endings to UTF-8 and LF
* Graphiti has its own release note
* .gitignore updated
* Unused Maven dependencies removed
* Add LICENCE file
* Update README.md
* Fix copyright header on most files (see ./releng/ scripts)
* Add .mailmap file for prettier git logs
* Use feature import instead of inclusion
* Add discovery sites in dev feature
* Remove unsupported target environments
* Update Checkstyle config file path in parent POM
* Add missing checkstyle jar
* Add Eclipse profile in parent POM to disable m2e configuration outside Eclipse
* Update wrapper scripts
* Cleanup releng files
* Update licensing
* Update headers
* Remove use of composite P2 repositories
* Add Jenkinsfile for Multibranch Pipeline projects
* Replace HashMap/Sets with LinkedHashMap/Sets
* Add TMF updates repo for latest XTend lib
* The dev feature now imports the requirements instead of including them (so that it can use already installed plugins/features)

### Bug fix
* Fix Checkstyle and Findbugs issues
* Fix few warnings that raised after Eclipse cleanup
* Fix plugin ID references from source
* Fix saxon reference and dependecy

## Release version 1.3.15 and earlier
* 2015.09.14 - See PREESM 2.2.1 release notes
