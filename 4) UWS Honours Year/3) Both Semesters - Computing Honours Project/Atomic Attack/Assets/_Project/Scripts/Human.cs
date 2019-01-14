using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Human : MonoBehaviour {

    [Space(-10), Header("[ Parent: Human ] Movement")]
    [SerializeField] Transform SpawnPoint;
    [SerializeField] float MoveSpeed;
    [SerializeField] Vector3 CurrentPosition;
    [SerializeField] bool Grounded;
    [SerializeField] float Velocity;

    [Space(10), Header("[ Parent: Human ] Health")]
    [SerializeField] float HealthMax;
    [SerializeField] float HealthCurrent;

    [Space(10), Header("[ Parent: Human ] Attack")]
    [SerializeField] float AttackRadius;
    [SerializeField] float AttackDamage;
    [SerializeField] float AttackSpeed;

    void Start()
    {
        if (this.gameObject.CompareTag("Enemy"))   { SpawnPoint = GameObject.Find("SpawnPoint Enemy").transform;  }
        if (this.gameObject.CompareTag ("Friend")) { SpawnPoint = GameObject.Find("SpawnPoint Friend").transform; }
    }

    void Update()
    {
        CurrentPosition = this.transform.position;
    }

    protected virtual void HP()
    {

    }

    protected virtual void Movespeed()
    {

    }
}