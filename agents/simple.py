from langgraph.prebuilt import create_react_agent
from langchain_ollama import ChatOllama

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
    prompt="You are a helpful assistant"
)

# Run the agent
response = agent.invoke(
    {"messages": [{"role": "user", "content": "what is the weather in sf"}]}
)
for msg in response['messages']:
    print(msg.content)