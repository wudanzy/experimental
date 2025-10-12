from langgraph.prebuilt import create_react_agent
from langchain_ollama import ChatOllama
from langgraph.checkpoint.memory import InMemorySaver

checkpointer = InMemorySaver()

ollama_llm = ChatOllama(
    model="qwen3:8b",
    temperature=0,
    # other params...
)
def get_weather(city: str) -> str:
    """Get weather for a given city."""
    return f"It's always sunny in {city}!"

agent = create_react_agent(
    model=ollama_llm,
    tools=[get_weather],
    checkpointer=checkpointer,
    prompt="You are a helpful assistant"
)

# Set the thread_id
config = {"configurable": {"thread_id": "1"}}
# Run the agent
sf_response = agent.invoke(
    {"messages": [{"role": "user", "content": "what is the weather in sf"}]},
    config
)
bj_response = agent.invoke(
    {"messages": [{"role": "user", "content": "how about beijing"}]},
    config
)
for msg in bj_response['messages']:
    print(msg.content)