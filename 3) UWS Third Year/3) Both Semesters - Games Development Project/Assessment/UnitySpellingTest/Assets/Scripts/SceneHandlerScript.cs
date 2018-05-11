using UnityEngine;

public class SceneHandlerScript : MonoBehaviour
{
    int lastSceneToPlay;

    // Use this for initialization
    void Start()
    {
        DontDestroyOnLoad(gameObject);
    }

    // Update is called once per frame
    void Update()
    {

    }

    public int GetSceneToPlay()
    {
        return lastSceneToPlay;
    }

    public void SetSceneToPlay(int thisScene)
    {
        lastSceneToPlay = thisScene;
    }
}