REM -compress -reusePack200Files 
set SITE=D:/Prog/repositories/graphiti-editor/site/
C:\eclipse\eclipse -application org.eclipse.equinox.p2.metadata.generator.EclipseGenerator -updateSite %SITE% -site file:%SITE%site.xml -metadataRepository file:%SITE% -metadataRepositoryName "Graphiti Update Site" -artifactRepository file:%SITE% -artifactRepositoryName "Graphiti Artifacts" -noDefaultIUs -vmargs -Xmx256m
