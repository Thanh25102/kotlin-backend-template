package com.khipster.template.khipstertemplate.modules.account

interface AccountService {
    fun save(account: CreateAccountDto): AccountDTO
    fun update(account: UpdateAccountDto): AccountDTO
    fun partialUpdate(account: UpdateAccountDto): AccountDTO
    fun deActivated(id: Long): AccountDTO
}