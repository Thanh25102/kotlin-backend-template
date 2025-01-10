package com.khipster.template.khipstertemplate.modules.smartcontrol

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "app_buttons")
class Buttons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @Size(max = 255)
    @Column(name = "name")
    var name: String? = null

    @Column(name = "value")
    var value: Int? = null

    @Size(max = 255)
    @Column(name = "camera_id")
    var cameraId: String? = null

    //OneToOne smart control
    @ManyToOne
    @JoinColumn(unique = true)
    var smartControl: SmartControls? = null

    constructor() {
    }

    constructor(
        id: Int? = null,
        name: String? = null,
        value: Int? = null,
        cameraId: String? = null,
        smartControl: SmartControls? = null
    ) {
        this.id = id
        this.name = name
        this.value = value
        this.cameraId = cameraId
        this.smartControl = smartControl
    }

    override fun toString(): String {
        return (
            "Buttons [id=$id, name=$name, value=$value, cameraId=$cameraId, smartControl=$smartControl]"
        )
    }
}