using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AI_Enemy : AI_Human {
    [SerializeField] protected float ThrowMultiplyer = 30;

    protected override void Start()
    {   // If GameObjectTag == Enemy, will target Friend.
        base.Start();
        FinalTarget = GameObject.Find("Manager Game").transform;
        InvokeRepeating("UpdateTargetFriend", 0f, 0.25f);
    }

    protected override void Update()
    {   // Changing to "Boundary" layer and destroy gameObject with ManagerGame OnTrigger2D to --PlayerLives. 
        if (transform.position.x <= -8 && Grounded == true && GrabbedByMouse == false) { gameObject.layer = 12; }
        if (RunAway == true) { MovementDirection = -1; }
        base.Update();
    }

    protected virtual void UpdateTargetFriend()
    {   // If GameObjectTag == Enemy, will target Friend.
        GameObject[] Enemies = GameObject.FindGameObjectsWithTag("Friend");
        float ShortestDistance = Mathf.Infinity;
        GameObject NearestEnemy = null;
        foreach (GameObject NewEnemy in Enemies)
        {   // ForEach algorithm to calculate Nearest Enemy.
            float DistanceToFriend = Vector2.Distance(transform.position, NewEnemy.transform.position);
            if (DistanceToFriend < ShortestDistance)
            {
                ShortestDistance = DistanceToFriend;
                NearestEnemy = NewEnemy;
            }
        }
        if (NearestEnemy != null && ShortestDistance <= LookRadius)
        {   // Updating Target to Nearest Enemy, and getting Target's Health.
            Target = NearestEnemy.transform;
            TargetHealth = NearestEnemy.GetComponent<HealthSystem>().Health;
            LookAtTarget();
        }
        else
        {   // If no more Enemies, look towards Friend Start Point.
            Target = FinalTarget;
            LookAtTarget();
        }
    }

    protected override void LookAtTarget()
    {   // Sprites flipping to look at its Target.
        if (OnTheCastle == true)
        {   // When on Castle, different Angle algorithm so will face Target correctly.
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.z, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection = -1; }
            if (Angle >= 170) { MovementDirection =  1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.left);
        }   // Else, LookAtTarget normally.
        else { base.LookAtTarget(); }
    }

    protected override void OnDrawGizmos()
    {
        Gizmos.color = Color.blue;
        Gizmos.DrawWireSphere(transform.position, AttackRadius);
        Gizmos.DrawWireSphere(transform.position, LookRadius);
    }

    #region Mouse Stuff
    protected virtual void OnMouseDown()
    {
        MovementSpeed = 0;
        GrabbedByMouse = true;
    }

    protected virtual void OnMouseDrag()
    {
        if (Input.mousePosition.y >= 130)
        {
            MovementSpeed = 0;
            Grounded = false;
            GrabbedByMouse = true;
            gameObject.layer = 10;  // Mouse Layer.
            // Calculating the ThrowVelocity.
            gameObject.GetComponent<Rigidbody2D>().gravityScale = 0;
            PreviousGrabbedPosition = transform.position;
            Vector3 newPosition = new Vector3(Input.mousePosition.x, Input.mousePosition.y, 10.0f);
            transform.position = Camera.main.ScreenToWorldPoint(newPosition);
        }
    }

    protected virtual void OnMouseUp()
    {
        gameObject.layer = 8;     // Enemy Layer.
        // Calculating the ThrowVelocity.
        Vector3 ThrowVector = transform.position - PreviousGrabbedPosition;
        float ThrowSpeed = ThrowVector.magnitude / Time.deltaTime;
        Vector3 ThrowVelocity = ThrowSpeed * ThrowVector.normalized;
        gameObject.GetComponent<Rigidbody2D>().velocity = ThrowVelocity / 5;
        gameObject.GetComponent<Rigidbody2D>().gravityScale = 1;
        // Adding fall damage. Mathf.Abs returns any value as true;
        float Damage = Mathf.Abs(ThrowVelocity.y * ThrowMultiplyer);
        GetComponent<HealthSystem>().DamageTaken((int)Damage);
        //Debug.Log("Throw Damage = " + ((int)Damage));
    }
    #endregion
}