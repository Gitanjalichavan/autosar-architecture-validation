package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object AutosarArchitectureValidation : BuildType({
    name = "Autosar Architecture Validation"

    artifactRules = """ci-scripts\compilation\compile.py"""

    params {
        password("user", "******", display = ParameterDisplay.HIDDEN)
        password("pass", "******", display = ParameterDisplay.HIDDEN)
    }
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
                scriptArguments = "%pass% %user%"
            }
        }
    }

    triggers {
        vcs {
        }
    }
})
