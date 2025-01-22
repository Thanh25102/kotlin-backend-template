import asyncio
import websockets
from websockets.exceptions import InvalidHandshake

# Token tích hợp bên thứ ba cần kiểm tra
THIRD_PARTY_TOKEN = "thanh"

async def handle_connection(websocket, path):
    try:
        # Lấy header từ kết nối WebSocket
        headers = websocket.request_headers

        # Kiểm tra token trong header
        token = headers.get("Authorization")
        if not token or token != f"Bearer {THIRD_PARTY_TOKEN}":
            print("Token không hợp lệ hoặc không có token.")
            await websocket.close(reason="Unauthorized")
            return

        print("Kết nối thành công với token hợp lệ!")

        # Lắng nghe và phản hồi client
        async for message in websocket:
            print(f"Received message: {message}")
            await websocket.send(f"Server received: {message}")

    except InvalidHandshake:
        print("Kết nối thất bại do handshake không hợp lệ.")
    except Exception as e:
        print(f"Lỗi: {e}")

async def main():
    # Khởi tạo WebSocket server
    server = await websockets.serve(
        handle_connection, "localhost", 8765
    )
    print("WebSocket server đang chạy trên ws://localhost:8765")

    await server.wait_closed()

# Chạy event loop
if __name__ == "__main__":
    asyncio.run(main())
