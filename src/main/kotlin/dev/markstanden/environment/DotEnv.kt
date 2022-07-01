package dev.markstanden.environment

import io.github.cdimascio.dotenv.dotenv

/**
 * Wraps the external dependency providing environment variables
 */
class DotEnv : FromEnvironment {
	private val env = dotenv { ignoreIfMissing }
	override fun get(key: EnvironmentVariables) =
		env[key.name] ?: ""
}