using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AI_Enemy : AI_Human {

    // Child Class AI_Enemy inheriting from Parent AI_Human.
    [Space( 10), Header("[^ Child: AI_Enemy ] Affected By")]
    public bool Stunned;
    public bool Blinded;
    protected Vector3 PreviousGrabbedPosition;
    [SerializeField] protected bool GrabbedByMouse;
    [SerializeField] protected bool OnCastle;


    protected override void Start()
    {   // If GameObjectTag == Enemy, will target Friend.
        base.Start();
        FinalTarget = GameObject.Find("Enemy End").transform;
        InvokeRepeating("UpdateTargetFriend", 0f, 0.25f);
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

    protected override void OnDrawGizmos()
    {
        Gizmos.color = Color.blue;
        Gizmos.DrawWireSphere(transform.position, AttackRadius);
        Gizmos.DrawWireSphere(transform.position, LookRadius);
    }

    /* -------------------------------------------------------------------------------------- */
    protected override void LookAtTarget()
    {
        if (OnCastle == true)
        {   // When on Castle, different Angle algorithm so will face Target correctly.
            Vector3 dir = Target.position - transform.position;
            float Angle = Mathf.Atan2(dir.z, dir.x) * Mathf.Rad2Deg;
            if (Angle <= 160) { MovementDirection = -1; }
            if (Angle >= 170) { MovementDirection =  1; Angle -= 180; }
            transform.rotation = Quaternion.AngleAxis(Angle, Vector3.left);
        }
        else { base.LookAtTarget(); }
    }

    protected override void Movement()
    {
        if (Grounded == true && GrabbedByMouse == false) { base.Movement(); }
        // For when chucked in air, only start coroutine after enemy has hit the ground.
        else if (Grounded == true && GrabbedByMouse == true) { StartCoroutine(HitTheGround()); }
    }

    protected virtual void OnMouseDown()
    {
        GrabbedByMouse = true;
        MovementSpeed = 0;
    }

    protected virtual void OnMouseDrag()
    {
        if (Input.mousePosition.y >= 130)
        {
            GrabbedByMouse = true;
            Grounded = false;
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

        // add fall damage
        //GetComponent<HealthSystem>().DamageTaken(1);

    }

    protected virtual IEnumerator HitTheGround()
    {
        gameObject.GetComponent<Animator>().Play("Die");
        yield return new WaitForSeconds(1f);
        GrabbedByMouse = false;
    }
}