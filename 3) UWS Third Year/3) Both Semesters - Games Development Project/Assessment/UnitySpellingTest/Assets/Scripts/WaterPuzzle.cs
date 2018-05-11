using UnityEngine;
using UnityEngine.SceneManagement;

public class WaterPuzzle : MonoBehaviour
{
    public Transform[] spawnPoints;
    public GameObject rockPrefab;
    private float timeToSpawnTimer;
    public float timeBetweenWaves = 1f;

    public string LevelToLoad;

    public void Start()
    {
        GameObject.Find("SceneHandler").GetComponent<SceneHandlerScript>().SetSceneToPlay(UnityEngine.SceneManagement.SceneManager.GetActiveScene().buildIndex);
    }

    void Update()
    {
        if (timeToSpawnTimer > 0)
            timeToSpawnTimer -= Time.deltaTime;
        else
        {
            SpawnRocks();
            timeToSpawnTimer = timeBetweenWaves;
        }

        if (Time.timeSinceLevelLoad >= 27)
        {
            Debug.Log("Time up");
            SceneManager.LoadScene(LevelToLoad);
        }
    }

    void SpawnRocks()
    {
        int randomIndex = Random.Range(0, spawnPoints.Length);

        for (int i = 0; i < spawnPoints.Length; i++)
        {
            if (randomIndex != i)
                Instantiate(rockPrefab, spawnPoints[i].position, Quaternion.identity);
        }
    }
}