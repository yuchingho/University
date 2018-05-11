using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerAttackController : MonoBehaviour
{
    Animator animator;
    bool fireballCastFinished = false;
    Canvas fireballCastCircle;

    // Use this for initialization
    void Start()
    {
        animator = GetComponent<Animator>();
        fireballCastCircle = GetComponent<Canvas>();
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetKeyDown(KeyCode.Space))
        {
            animator.Play("fireballCastCircleAnim");            
        }
        else if (Input.GetKeyUp(KeyCode.Space))
        {
            if (fireballCastFinished)
            {
                print("Launch the attack");
                animator.Play("fireballNotCastedAnim");
            }
            else
                animator.Play("fireballNotCastedAnim");
        }
    }

    public void AlertObservers(string message)
    {
        if (message.Equals("fireballCastCircleEnded"))
            fireballCastFinished = true;
    }
}
