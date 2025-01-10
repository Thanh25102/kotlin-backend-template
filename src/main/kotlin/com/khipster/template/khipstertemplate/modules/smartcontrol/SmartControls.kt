package com.khipster.template.khipstertemplate.modules.smartcontrol

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "app_smart_control")
class SmartControls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "name")
    var name: String? = null

    @Size(max = 255)
    @Column(name = "domain")
    var domain: String? = null

    @OneToMany(mappedBy = "smartControl")
    var buttons: MutableList<Buttons> = mutableListOf()

    constructor()

    constructor(id: Int? = null, name: String? = null, domain: String? = null, buttons: MutableList<Buttons>? = mutableListOf()) {
        this.id = id
        this.name = name
        this.domain = domain
        if (buttons != null) {
            this.buttons = buttons
        }
    }

    override fun toString(): String {
        return (
                "SmartControls [id=$id, name=$name, domain=$domain, buttons=$buttons]"
        )
    }
}