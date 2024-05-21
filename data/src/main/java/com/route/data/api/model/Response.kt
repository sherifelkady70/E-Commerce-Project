package com.route.data.api.model

import com.google.gson.annotations.SerializedName

data class Response<T>(

	@field:SerializedName("metadata")
	val metadata: Pagination? = null,

	@field:SerializedName("data")
	val data: T? = null,

	@field:SerializedName("results")
	val results: Int? = null ,

	@field:SerializedName("results")
	val statusMsg: String? = null ,

	@field:SerializedName("results")
	val message: String? = null

)