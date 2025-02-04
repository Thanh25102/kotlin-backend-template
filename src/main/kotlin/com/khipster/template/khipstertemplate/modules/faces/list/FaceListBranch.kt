package com.khipster.template.khipstertemplate.modules.faces.list

import com.khipster.template.khipstertemplate.modules.attendance.branch.Branch
import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "face_branch_group")
data class FaceListBranch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @get: NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    var branch: Branch? = null,

    @Column(name = "list_id")
    var listId: String? = null,
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as FaceListBranch

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}