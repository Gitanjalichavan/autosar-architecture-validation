import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(HttpsGithubComGitanjalichavanAutosarArchitectureValidationRefsHeadsDevelopment1)

    buildType(C4kGenerationAndBuild)
    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        python {
            name = "Validation"
            command = file {
                filename = "ci-scripts/validation/validation.py"
            }
        }
        python {
            name = "Rte_Generation"
            command = file {
                filename = "ci-scripts/generation/rteGeneration.py"
            }
        }
        python {
            name = "Compilation"
            command = file {
                filename = "ci-scripts/compilation/compile.py"
            }
        }
        python {
            name = "Upload"
            command = file {
                filename = "ci-scripts/compilation/upload.py"
            }
        }
    }

    triggers {
        vcs {
        }
    }
})

object C4kGenerationAndBuild : BuildType({
    name = "c4k_generation_and_build"

    vcs {
        root(HttpsGithubComGitanjalichavanAutosarArchitectureValidationRefsHeadsDevelopment1)
    }

    steps {
        python {
            name = "pre_build"
            command = file {
                filename = "ci-scripts/generation/rteGeneration.py"
            }
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComGitanjalichavanAutosarArchitectureValidationRefsHeadsDevelopment1 : GitVcsRoot({
    name = "https://github.com/Gitanjalichavan/autosar-architecture-validation#refs/heads/development (1)"
    url = "https://github.com/Gitanjalichavan/autosar-architecture-validation"
    branch = "refs/heads/development"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "Gitanjalichavan"
        password = "credentialsJSON:68cb26d6-88f8-41d3-9d2a-b48edfff6207"
    }
})
