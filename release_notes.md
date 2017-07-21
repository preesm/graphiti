Graphiti Changelog
==================

## Release version X.Y.Z
*XXXX.XX.XX*

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
