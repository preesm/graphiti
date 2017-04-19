Graphiti Changelog
==================

## Release version 1.3.16
*2017.04.19*

### New Feature
* Build process now produces a source feature (include source code)
* Build process now produces a 'meta' feature including all development requirements for Graphiti
* Add test plug-in fragments for future test campaigns
* Maven build process allows to automatically deploy on SourceForge server (needs Unix OS)
  * On windows, it still builds the complete site, but stops before the upload

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

### Bug fix
* Fix Checkstyle and Findbugs issues
* Fix few warnings that raised after Eclipse cleanup

## Release version 1.3.15
* 2015.09.14 - See PREESM 2.2.1 release notes 
