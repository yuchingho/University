﻿using UnityEngine;
using System.Collections;

public class PlayerController2 : MonoBehaviour {

    public Transform LeftArm;
    public Transform RightArm;
    public Transform LeftLeg;
    public Transform RightLeg;
	
	// Update is called once per frame
	void Update () 
    {
        if (Input.GetKeyDown(KeyCode.W))
        {
            LeftArm.GetComponent<Rigidbody2D>().AddForce(LeftArm.right * 30f);
        }
        if (Input.GetKeyDown(KeyCode.Q))
        {
            LeftArm.GetComponent<Rigidbody2D>().AddForce(-LeftArm.right * 30f);
        }

        if (Input.GetKeyDown(KeyCode.P))
        {
            RightArm.GetComponent<Rigidbody2D>().AddForce(RightArm.right * 30f);
        }
        if (Input.GetKeyDown(KeyCode.O))
        {
            RightArm.GetComponent<Rigidbody2D>().AddForce(-RightArm.right * 30f);
        }
	}
}
